import asyncio



async def do_tasks(to_do_list, max_concurrent):
    async def run_task(semaphore, i, duration):
        async with semaphore:
            await asyncio.sleep(duration)
            return i*i

    semaphore = asyncio.Semaphore(max_concurrent)
    tasks = [asyncio.create_task(run_task(semaphore, i, duration)) for i, duration in to_do_list]
    return await asyncio.gather(*tasks)

